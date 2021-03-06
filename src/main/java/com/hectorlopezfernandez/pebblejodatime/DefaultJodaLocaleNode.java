package com.hectorlopezfernandez.pebblejodatime;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.NodeVisitor;
import com.mitchellbosecke.pebble.node.AbstractRenderableNode;
import com.mitchellbosecke.pebble.node.expression.Expression;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;
import com.mitchellbosecke.pebble.template.ScopeChain;

public class DefaultJodaLocaleNode extends AbstractRenderableNode {

    private final Expression<?> value;

    public DefaultJodaLocaleNode(int lineNumber, Expression<?> value) {
        super(lineNumber);
        this.value = value;
    }

    @Override
    public void render(PebbleTemplateImpl self, Writer writer, EvaluationContext context) throws PebbleException, IOException {
    	// evaluate and parse locale
    	Object evaluatedLocale = value.evaluate(self, context);
    	Locale locale = null;
    	if (evaluatedLocale instanceof String) {
    		locale = new Locale.Builder().setLanguageTag((String) evaluatedLocale).build();
    	} else if (evaluatedLocale instanceof Locale) {
    		locale = (Locale) evaluatedLocale;
    	} else {
    		throw new IllegalArgumentException("DefaultJodaLocale only supports String and Locale locales. Actual argument was: " + (evaluatedLocale == null ? "null" : evaluatedLocale.getClass().getName()));
    	}
    	ScopeChain values = context.getScopeChain();
    	values.put(JodaExtension.LOCALE_REQUEST_ATTRIBUTE, locale);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}