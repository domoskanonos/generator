import {BasicEnumRepository} from "@domoskanonos/frontend-basis";

export class TemplateBasicEnumRepository extends BasicEnumRepository {

    private static uniqueInstance: TemplateBasicEnumRepository;

    static getUniqueInstance() {
        if (!TemplateBasicEnumRepository.uniqueInstance) {
            TemplateBasicEnumRepository.uniqueInstance = new TemplateBasicEnumRepository();
        }
        return TemplateBasicEnumRepository.uniqueInstance;
    }

}