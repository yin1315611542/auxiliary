package cn.goldencis.auxiliary;

import cn.goldencis.auxiliary.infrastructure.common.CommandUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SpelExpressionTest {
    //测试SPEL表达式的填装及解析
    @Test
    public void test1() throws IOException {
        String s = CommandUtil.commandExecute2("curl -sS -XGET \"http://localhost:9200/_cat/indices\" | awk '{print $3}' | while read index; do curl -sS -XGET \"http://localhost:9200/$index/_settings\" | jq '.[].settings.index.blocks.read_only_allow_delete' | grep -q \"true\" && echo \"{\\\"name\\\":\\\"$index\\\"}\"; done | sed -e ':a' -e 'N' -e '$!ba' -e 's/\\n/,/g' | sed 's/^/[/' | sed 's/$/]/'s\n").getMessage();
        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonNode> jsonNodes = objectMapper.readValue(s, new TypeReference<List<JsonNode>>() {
        });
        //创建表达式计算上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        jsonNodes.forEach(jsonNode -> {
            jsonNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                evaluationContext.setVariable(key, value.asText());
            });
        });
        //创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        //创建解析器上下文
        ParserContext context = new TemplateParserContext("%{", "}");
        Expression expression = parser.parseExpression("监测到处于read_only状态的索引%{#name}", context);
        //获取值
        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);
    }
}
