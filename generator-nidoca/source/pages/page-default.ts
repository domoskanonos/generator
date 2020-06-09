import { html, property, TemplateResult } from 'lit-element';
import { I18nService, SecureService } from '@domoskanonos/frontend-basis';
import { TypographyType, NidocaTemplate } from '@domoskanonos/nidoca-core';

export abstract class DefaultPage extends NidocaTemplate {

   @property()
   isAuthenticated: boolean = true;
   //isAuthenticated: boolean = SecureService.getUniqueInstance().isAuthenticated();

   @property()
   navigationTitle: string = '';

   getTopContent(): TemplateResult {
      return html`
         <nidoca-top-app-bar>
            ${this.getTopLeftComponent()} ${this.getTopMainComponent()}
            ${this.getTopRightComponent()}
         </nidoca-top-app-bar>
      `;
   }

   getTopLeftComponent(): TemplateResult {
      return html`
         <nidoca-icon
            title="${I18nService.getUniqueInstance().getValue('menu')}"
            slot="leftComponents"
            icon="menu"
            clickable="true"
         ></nidoca-icon>
         <nidoca-typography
            slot="leftComponents"
            .typographyType="${TypographyType.BODY1}"
            >${this.navigationTitle}</nidoca-typography
         >
      `;
   }

   getTopMainComponent(): TemplateResult {
      return html``;
   }

   getTopRightComponent(): TemplateResult {
      return html``;
   }

   getLeftNavigationContent(): TemplateResult {
        return html`
            <nidoca-navigation-link
               slot="links"
               icon="dashboard"
               text="${I18nService.getUniqueInstance().getValue(
                  'home'
               )}"
               href="dashboard"
            ></nidoca-navigation-link>
            <nidoca-navigation-section
                    .rendered="${this.isAuthenticated}"
                    slot="links"
                    text="${I18nService.getUniqueInstance().getValue("section_search")}"
            ></nidoca-navigation-section>
            <nidoca-navigation-link
               slot="links"
               icon="search"
               text="${I18nService.getUniqueInstance().getValue("process_nav_list")}"
               href="processlist"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
                        <nidoca-navigation-link
               slot="links"
               icon="search"
               text="${I18nService.getUniqueInstance().getValue("project_nav_list")}"
               href="projectlist"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
             <nidoca-navigation-link
               slot="links"
               icon="search"
               text="${I18nService.getUniqueInstance().getValue("property_nav_list")}"
               href="propertylist"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
            <nidoca-navigation-link
               slot="links"
               icon="search"
               text="${I18nService.getUniqueInstance().getValue("item_nav_list")}"
               href="itemlist"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
           <nidoca-navigation-section
                .rendered="${this.isAuthenticated}"
                 slot="links"
                 text="${I18nService.getUniqueInstance().getValue("section_add")}"
            ></nidoca-navigation-section>
             <nidoca-navigation-link
               slot="links"
               icon="add"
               text="${I18nService.getUniqueInstance().getValue("property_nav_edit")}"
               href="propertyedit"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
             <nidoca-navigation-link
               slot="links"
               icon="add"
               text="${I18nService.getUniqueInstance().getValue("item_nav_edit")}"
               href="itemedit"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
             <nidoca-navigation-link
               slot="links"
               icon="add"
               text="${I18nService.getUniqueInstance().getValue("project_nav_edit")}"
               href="projectedit"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
             <nidoca-navigation-link
               slot="links"
               icon="add"
               text="${I18nService.getUniqueInstance().getValue("process_nav_edit")}"
               href="processedit"
               .rendered="${this.isAuthenticated}"
            ></nidoca-navigation-link>
            <nidoca-divider slot="links"></nidoca-divider>
            <nidoca-navigation-link
               slot="links"
               icon="settings"
               text="${I18nService.getUniqueInstance().getValue(
                  'settings'
               )}"
               href="settings"
            ></nidoca-navigation-link>
         `;
   }

}
