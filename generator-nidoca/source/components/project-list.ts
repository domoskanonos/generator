import {
NidocaAbstractComponentSearchList
} from '@domoskanonos/nidoca-app';
import { PageableContainer, RouterService, I18nService } from '@domoskanonos/frontend-basis';
import { customElement, html, TemplateResult } from 'lit-element';
import {
TypographyType,
FlexJustifyContent,
FlexAlignItems,
SpacerAlignment,
SpacerSize,
FlexContainerProperties
} from '@domoskanonos/nidoca-core';
import {ProjectRemoteRepository} from '../../repo/project-repository';
import {ProjectModel} from '../model/project-model';

@customElement('project-search-list-component')
export class ProjectModelSearchNidocaList extends NidocaAbstractComponentSearchList<ProjectModel> {

    async executeSearch(search: string): Promise<ProjectModel[]> {
        let pageableContainer: PageableContainer<ProjectModel> = await ProjectRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&technicalDescriptor=')
                .concat(search)
                .concat('&javaBasePackage=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(projectmodel: ProjectModel) : TemplateResult {
        return html`
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MAX}"
    spacerAlignment="${SpacerAlignment.HORIZONTAL}"
    >
        <nidoca-flex-container
        .flexContainerProperties="${[FlexContainerProperties.CONTAINER_WIDTH_100]}"
        itemFlexBasisValue="100%"
        .justifyContent="${FlexJustifyContent.FLEX_START}"
        .alignItems="${FlexAlignItems.CENTER}"
        >
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${projectmodel.id}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${projectmodel.itemEntities}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${projectmodel.technicalDescriptor}"></nidoca-typography>
            <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${projectmodel.javaBasePackage}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(projectmodel: ProjectModel, index: number): void {
        console.debug("ProjectModel list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('${model.getModelEditPageUrl()}',{ 'id' : projectmodel.id});
    }

   renderNoRecord(): TemplateResult {
      return html`
         <nidoca-flex-container
            .flexContainerProperties="${[FlexContainerProperties.CONTAINER_WIDTH_100]}"
            itemFlexBasisValue="auto"
            .flexJustifyContent="${FlexJustifyContent.SPACE_AROUND}"
            .alignItems="${FlexAlignItems.CENTER}"
         >
            <nidoca-spacer
               .spacerSize="${SpacerSize.BIG}"
               spacerAlignment="${SpacerAlignment.BOTH}"
            >
               <nidoca-typography
                  .typographyType="${TypographyType.BUTTON}"
                  text="${I18nService.getUniqueInstance().getValue(
                     'no_entry_found'
                  )}"
               ></nidoca-typography>
            </nidoca-spacer>
         </nidoca-flex-container>
      `;
   }

   async deleteItems(projectmodels: ProjectModel[]): Promise<void> {
        projectmodels.forEach((projectmodel) => {
             console.log('delete projectmodel with id: {}', projectmodel.id);
        ProjectRemoteRepository.getUniqueInstance().delete(projectmodel.id);
        });
   }

}
