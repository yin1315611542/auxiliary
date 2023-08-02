package cn.goldencis.auxiliary.domain.step;

import cn.goldencis.auxiliary.domain.step.enumstep.StepType;
import cn.goldencis.auxiliary.domain.step.enumstep.StepTypeConverter;
import cn.goldencis.auxiliary.infrastructure.condition.ConditionType;
import cn.goldencis.auxiliary.infrastructure.condition.ConditionTypeConverter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 16:38
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "step")
public class Step implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String StepName;

    @Convert(converter = StepTypeConverter.class)
    private StepType StepType;

    private String excContent;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long level;

    private Long parentId;

    private Long solutionId;

    private String execResult;

    private String returnType;

    private static final String PATTERN = "%\\{[^}]*}";

    private String parameterType;

    @Transient
    private List<Step> childStep = new ArrayList<>();
    @Transient
    private List<JsonNode> parameter;

    private String excCondition;
    @Convert(converter = ConditionTypeConverter.class)
    private ConditionType conditionType;

    public static boolean containsPlaceholder(String str) {
        if (ObjectUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public String initExcContent(JsonNode parameter) {
        //创建表达式计算上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        parameter.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();
            evaluationContext.setVariable(key, value.asText());
        });
        //创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        //创建解析器上下文
        ParserContext context = new TemplateParserContext("%{", "}");
        Expression expression = parser.parseExpression(excContent, context);
        //获取值
        return expression.getValue(evaluationContext, String.class);
    }

    public Step initExcCondition(List<JsonNode> jsonNodes) {
        //创建表达式计算上下文
        if (ObjectUtils.isEmpty(jsonNodes) || !containsPlaceholder(excCondition)) {
            return this;
        }
        return this.setExcCondition(evaluation(jsonNodes, excCondition, ""));
    }

    public Step initExcContent(List<JsonNode> jsonNodes) {
        //创建表达式计算上下文
        if (ObjectUtils.isEmpty(jsonNodes) || !containsPlaceholder(excContent)) {
            return this;
        }
        return this.setExcContent(evaluation(jsonNodes, excContent, ";"));
    }

    public String evaluation(List<JsonNode> jsonNodes, String cmd, String join) {

        EvaluationContext evaluationContext = new StandardEvaluationContext();
        StringBuilder cmds = new StringBuilder();
        jsonNodes.forEach(jsonNode -> {
            jsonNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                evaluationContext.setVariable(key, value.asText());
            });
            //创建解析器
            SpelExpressionParser parser = new SpelExpressionParser();
            //创建解析器上下文
            ParserContext context = new TemplateParserContext("%{", "}");
            Expression expression = parser.parseExpression(cmd, context);
            cmds.append(expression.getValue(evaluationContext, String.class)).append(join);
        });
        return cmds.toString();
    }


}
