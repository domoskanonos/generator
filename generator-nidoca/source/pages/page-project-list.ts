import {customElement} from 'lit-element';
import {
NidocaAbstractPageSearchList
} from '@domoskanonos/nidoca-app';
import {I18nService} from "@domoskanonos/frontend-basis";
import {ProjectSearchNidocaList} from '../components/project-list';

@customElement('project-search-list-page')
export class ProjectSearchListPage extends NidocaAbstractPageSearchList {

   constructor() {
      super(new ProjectSearchNidocaList());
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
