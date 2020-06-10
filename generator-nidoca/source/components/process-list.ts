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
import {ProcessRemoteRepository} from '../repo/process-repository';
import {Process} from '../model/model';

@customElement('process-search-list-component')
export class ProcessSearchNidocaList extends NidocaAbstractComponentSearchList<Process> {

    async executeSearch(search: string): Promise<Process[]> {
        let pageableContainer: PageableContainer<Process> = await ProcessRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&processTempPath=')
                .concat(search)
                .concat('&processParentPath=')
                .concat(search)
                .concat('&technicalDescriptor=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(process: Process) : TemplateResult {
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
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${process.id}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${process.projects}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${process.processTempPath}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${process.processParentPath}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${process.technicalDescriptor}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${process.deactivated}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(process: Process, index: number): void {
        console.debug("Process list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('processedit',{ 'id' : process.id});
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

   async deleteItems(processs: Process[]): Promise<void> {
        processs.forEach((process) => {
             console.log('delete process with id: {}', process.id);
        ProcessRemoteRepository.getUniqueInstance().delete(process.id);
        });
   }

}
