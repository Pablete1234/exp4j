package net.objecthunter.exp4j;

import net.objecthunter.exp4j.function.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface ExpressionContext {

    Set<String> getVariables();
    Double getVariable(String name);

    Set<String> getFunctions();
    Function getFunction(String name);

    default Function getFunction(Function fn) {
        Function override = getFunction(fn.getName());
        return override != null ? override : fn;
    }

    static ExpressionContext of(Set<String> variables, Set<String> functions) {
        Map<String, Double> vars = new HashMap<>();
        variables.forEach(v -> vars.put(v, null));
        return of(vars, functions);
    }

    static ExpressionContext of(Map<String, Double> variables, Set<String> functions) {
        return new Impl(variables, functions);
    }

    class Impl implements ExpressionContext {
        private final Map<String, Double> variables;
        private final Set<String> functions;

        public Impl(Map<String, Double> variables, Set<String> functions) {
            this.variables = variables;
            this.functions = functions;
        }

        public void setVariable(String name, double value) {
            this.variables.put(name, value);
        }

        @Override
        public Set<String> getVariables() {
            return variables.keySet();
        }

        @Override
        public Double getVariable(String name) {
            return variables.get(name);
        }

        @Override
        public Set<String> getFunctions() {
            return functions;
        }

        @Override
        public Function getFunction(String name) {
            return null;
        }

        @Override
        public Function getFunction(Function fn) {
            return fn;
        }
    }


}
