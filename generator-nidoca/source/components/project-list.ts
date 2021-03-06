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
import {ProjectRemoteRepository} from '../repo/project-repository';
import {Project} from '../model/model';

@customElement('project-search-list-component')
export class ProjectSearchNidocaList extends NidocaAbstractComponentSearchList<Project> {

    async executeSearch(search: string): Promise<Project[]> {
        let pageableContainer: PageableContainer<Project> = await ProjectRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&technicalDescriptor=')
                .concat(search)
                .concat('&namespace=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(project: Project) : TemplateResult {
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
        flexItemBasisValue="100%"
        .justifyContent="${FlexJustifyContent.FLEX_START}"
        .alignItems="${FlexAlignItems.CENTER}"
        >
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${project.id}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${project.items}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${project.template}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${project.technicalDescriptor}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${project.namespace}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${project.deactivated}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(project: Project, index: number): void {
        console.debug("Project list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('projectedit',{ 'id' : project.id});
    }

   renderNoRecord(): TemplateResult {
      return html`
         <nidoca-flex-container
            .flexContainerProperties="${[FlexContainerProperties.CONTAINER_WIDTH_100]}"
            flexItemBasisValue="auto"
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

   async deleteItems(projects: Project[]): Promise<void> {
        projects.forEach((project) => {
             console.log('delete project with id: {}', project.id);
        ProjectRemoteRepository.getUniqueInstance().delete(project.id);
        });
   }

}
