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
import {AuthUserRemoteRepository} from '../repo/authuser-repository';
import {AuthUser} from '../model/authuser-model';

@customElement('authuser-search-list-component')
export class AuthUserSearchNidocaList extends NidocaAbstractComponentSearchList<AuthUser> {

    async executeSearch(search: string): Promise<AuthUser[]> {
        let pageableContainer: PageableContainer<AuthUser> = await AuthUserRemoteRepository.getUniqueInstance().search(
            0,
            this.resultSize,
            '', ''
                .concat('&email=')
                .concat(search)
                .concat('&password=')
                .concat(search)
                .concat('&firstName=')
                .concat(search)
                .concat('&lastName=')
                .concat(search)
                .concat('&city=')
                .concat(search)
        );
        return pageableContainer.content;
    }

    renderListItem(authuser: AuthUser) : TemplateResult {
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
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.id}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.email}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.password}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.firstName}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.lastName}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.birthday}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.active}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.acceptTermsOfUse}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.city}"></nidoca-typography>
                    <nidoca-typography .typographyType="${TypographyType.BODY1}" text="${authuser.roles}"></nidoca-typography>
        </nidoca-flex-container>
    </nidoca-spacer>
    <nidoca-spacer
    .spacerSize="${SpacerSize.MEDIUM}"
    spacerAlignment="${SpacerAlignment.VERTICAL}"
    ></nidoca-spacer>
      `;
    }

    itemClicked(authuser: AuthUser, index: number): void {
        console.debug("AuthUser list item clicked, index= {0}", index);
        RouterService.getUniqueInstance().navigate('authuseredit',{ 'id' : authuser.id});
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

   async deleteItems(authusers: AuthUser[]): Promise<void> {
        authusers.forEach((authuser) => {
             console.log('delete authuser with id: {}', authuser.id);
        AuthUserRemoteRepository.getUniqueInstance().delete(authuser.id);
        });
   }

}
