import {customElement} from 'lit-element';
import {
NidocaAbstractPageSearchList
} from '@domoskanonos/nidoca-app';
import {I18nService} from "@domoskanonos/frontend-basis";
import {PropertySearchNidocaList} from '../components/property-list';

@customElement('property-search-list-page')
export class PropertySearchListPage extends NidocaAbstractPageSearchList {

   constructor() {
      super(new PropertySearchNidocaList());
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
