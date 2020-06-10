import { customElement, html, query, TemplateResult } from 'lit-element';
import {DefaultPage} from "../pages/page-default";
import {
   I18nService,
   RouterService
} from '@domoskanonos/frontend-basis';
import {
FlexAlignItems,
FlexContainerProperties,
FlexDirection,
FlexJustifyContent,
FlexWrap,
GridAlignItems,
GridJustifyItems,
SpacerAlignment,
SpacerSize
} from '@domoskanonos/nidoca-core';
import {
NidocaAbstractComponentEdit
} from '@domoskanonos/nidoca-app';
import {Project} from "../model/project-model";

@customElement('project-edit-page')
export class ProjectEditPage extends DefaultPage {

   constructor() {
      super();
      this.navigationTitle = I18nService.getUniqueInstance().getValue(
         'project_nav_edit'
      );
   }

   @query('#editComponent')
   editComponent: NidocaAbstractComponentEdit<Project> | undefined;

   getTopRightComponent(): TemplateResult {
      return html`
         <nidoca-icon
            title="${I18nService.getUniqueInstance().getValue('cancel')}"
            slot="rightComponents"
            icon="${RouterService.getUniqueInstance().getStateProperty('id') != null
               ? 'arrow_back'
               : 'cancel'}"
            clickable="true"
            @nidoca-event-icon-clicked="${() =>
               RouterService.getUniqueInstance().back()}"
         ></nidoca-icon>
         <nidoca-icon
            title="${I18nService.getUniqueInstance().getValue('save')}"
            .rendered="${true}"
            slot="rightComponents"
            icon="save"
            clickable="true"
            @nidoca-event-icon-clicked="${() => this.save()}"
         ></nidoca-icon>
         <nidoca-icon
            title="${I18nService.getUniqueInstance().getValue('delete')}"
            .rendered="${RouterService.getUniqueInstance().getStateProperty(
               'id'
            ) != null}"
            slot="rightComponents"
            icon="delete"
            clickable="true"
            @nidoca-event-icon-clicked="${() => this.delete()}"
         ></nidoca-icon>
      `;
   }

 getMainComponent(): TemplateResult {
    return html`
      <nidoca-grid-container
        .gridJustifyItems="${GridJustifyItems.CENTER}"
        .gridAlignItems="${GridAlignItems.START}"
        .gridTemplateRows="${['1fr']}"
        .gridTemplateColumns="${['1fr']}"
        height="100vh"
      >
        <nidoca-flex-container
          style="width: 400px;"
          .flexContainerProperties="${[
            FlexContainerProperties.CONTAINER_WIDTH_100,
            FlexContainerProperties.SMARTPHONE_MAX_WIDTH,
            FlexContainerProperties.SMARTPHONE_HORIZONTAL_PADDING,
          ]}"
          flexItemBasisValue="auto"
          .flexDirection="${FlexDirection.COLUMN}"
          .flexWrap="${FlexWrap.NO_WRAP}"
          .flexJustifyContent="${FlexJustifyContent.SPACE_AROUND}"
          .flexAlignItems="${FlexAlignItems.STRETCH}"
        >
          <nidoca-icon
            color="var(--app-color-primary-background)"
            size="128"
            icon="edit"
            .withIconSpace="${false}"
          ></nidoca-icon>
          <nidoca-spacer
            style="text-align:center;"
            spacerSize="${SpacerSize.MEDIUM}"
            .spacerAlignment="${SpacerAlignment.VERTICAL}"
          >
          </nidoca-spacer>
           <project-edit-component  id="editComponent"></project-edit-component>
         </nidoca-flex-container>
      </nidoca-grid-container>
    `;
  }

   private save() {
      if (this.editComponent?.identifier == null) {
         this.editComponent?.saveItem();
      } else {
         this.editComponent?.updateItem();
      }
   }

   private delete() {
      if (this.editComponent) {
         this.editComponent.showDeleteDialog = true;
      }
   }

}
