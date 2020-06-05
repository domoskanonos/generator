import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {Item} from "../model/item-model";

export class ItemRemoteRepository extends BasicRemoteRepository<Item, number> {

    private static uniqueInstance: ItemRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"/ITEM");
    }

    static getUniqueInstance() {
        if (!ItemRemoteRepository.uniqueInstance) {
            ItemRemoteRepository.uniqueInstance = new ItemRemoteRepository();
        }
        return ItemRemoteRepository.uniqueInstance;
    }

}