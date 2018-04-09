package com.niafikra.inaya;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

@Tag("inaya-header")
@HtmlImport("frontend://engine/vaadinUI/src/main/inaya-header.html")
public class InayaHeader extends PolymerTemplate<InayaHeader.InayaHeaderModel> {

    public interface InayaHeaderModel extends TemplateModel {

    }
}
