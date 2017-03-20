package jstl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Transland extends TagSupport {
    static final Logger LOG = LoggerFactory.getLogger(Transland.class);
    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("Transland");
        } catch(IOException e) {
            LOG.warn("Error: " + e.getMessage());
        }
        return SKIP_BODY;
    }
}
