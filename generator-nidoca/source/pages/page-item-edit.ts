import { customElement, html, query, TemplateResult } from 'lit-element';
import {DefaultPage} from "../pages/page-default";
import {
   I18nService,
   RouterService
} from '@domoskanonos/frontend-basis';
import {
NidocaAbstractComponentEdit
} from '@domoskanonos/nidoca-app';
import {Item} from "../model/item-model";

@customElement('item-edit-page')
export class ItemEditPage extends DefaultPage {

   constructor() {
      super();
      this.navigationTitle = I18nService.getUniqueInstance().getValue(
         '${model.getI18nEditPageTitleKey()}'
      );
   }

   @query('#editComponent')
   editComponent: NidocaAbstractComponentEdit<Item> | undefined;

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
         <item-edit-component  id="editComponent"></item-edit-component>
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
