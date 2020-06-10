import {BasicEnumRepository} from "@domoskanonos/frontend-basis";

export class LanguageTypeBasicEnumRepository extends BasicEnumRepository {

    private static uniqueInstance: LanguageTypeBasicEnumRepository;

    static getUniqueInstance() {
        if (!LanguageTypeBasicEnumRepository.uniqueInstance) {
            LanguageTypeBasicEnumRepository.uniqueInstance = new LanguageTypeBasicEnumRepository();
        }
        return LanguageTypeBasicEnumRepository.uniqueInstance;
    }

}