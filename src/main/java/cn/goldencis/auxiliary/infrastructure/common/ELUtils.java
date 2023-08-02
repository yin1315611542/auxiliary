package cn.goldencis.auxiliary.infrastructure.common;

import javax.el.*;

public class ELUtils {
    private static final ExpressionFactory factory = ExpressionFactory.newInstance();

    public static boolean evaluate(String expression) {
        ValueExpression valueExp = factory.createValueExpression(createELContext(), expression, boolean.class);
        return (Boolean) valueExp.getValue(createELContext());
    }

    private static javax.el.ELContext createELContext() {
        CompositeELResolver resolver = new CompositeELResolver();
        resolver.add(new MapELResolver());
        resolver.add(new ResourceBundleELResolver());
        resolver.add(new ListELResolver());
        resolver.add(new BeanELResolver());
        return new javax.el.ELContext() {
            @Override
            public javax.el.VariableMapper getVariableMapper() {
                return null;
            }

            @Override
            public javax.el.FunctionMapper getFunctionMapper() {
                return null;
            }

            @Override
            public javax.el.ELResolver getELResolver() {
                return resolver;
            }
        };
    }
}