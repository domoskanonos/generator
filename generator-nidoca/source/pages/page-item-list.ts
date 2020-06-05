import {customElement} from 'lit-element';
import {
NidocaAbstractPageSearchList
} from '@domoskanonos/nidoca-app';
import {I18nService} from "@domoskanonos/frontend-basis";
import {ItemSearchNidocaList} from '../components/item-list';

@customElement('item-search-list-page')
export class ItemSearchListPage extends NidocaAbstractPageSearchList {

   constructor() {
      super(new ItemSearchNidocaList());
   }

   getNavigationTitle(): string {
      return I18nService.getUniqueInstance().getValue('${model.getI18nSearchListPageTitleKey()}');
   }

   getAddTitle(): string {
      return I18nService.getUniqueInstance().getValue('${model.getI18nAddKey()}');
   }

   getEditPageUrl(): string {
      return '${model.getModelEditPageUrl()}';
   }
}
