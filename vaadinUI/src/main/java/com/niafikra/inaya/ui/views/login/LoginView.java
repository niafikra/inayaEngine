package com.niafikra.inaya.ui.views.login;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Tag("login-view")
@HtmlImport("context://webjars/inaya-engine/src/views/login/login-view.html")
@Route(value = "login")
@PageTitle("inaya")
@Theme(Lumo.class)
public class LoginView extends PolymerTemplate<LoginView.Model> implements PageConfigurator, AfterNavigationListener {

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        boolean error = event.getLocation().getQueryParameters().getParameters().containsKey("error");
        getModel().setError(error);
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        // Force login page to use Shady DOM to avoid problems with browsers and
        // password managers not supporting shadow DOM
        settings.addInlineWithContents(InitialPageSettings.Position.PREPEND,
                "window.customElements=window.customElements||{};" +
                        "window.customElements.forcePolyfill=true;" +
                        "window.ShadyDOM={force:true};", InitialPageSettings.WrapMode.JAVASCRIPT);

    }

    public interface Model extends TemplateModel {
        void setError(boolean error);
    }
}
