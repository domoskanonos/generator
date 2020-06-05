import {customElement} from 'lit-element';
import {
NidocaAbstractPageSearchList
} from '@domoskanonos/nidoca-app';
import {I18nService} from "@domoskanonos/frontend-basis";
import {ProcessSearchNidocaList} from '../components/process-list';

@customElement('process-search-list-page')
export class ProcessSearchListPage extends NidocaAbstractPageSearchList {

   constructor() {
      super(new ProcessSearchNidocaList());
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
