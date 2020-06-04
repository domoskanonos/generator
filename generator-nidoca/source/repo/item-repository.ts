import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {ItemModel} from "../model/item-model";

export class ItemRemoteRepository extends BasicRemoteRepository<ItemModel, number> {

    private static uniqueInstance: ItemRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"ITEM");
    }

    static getUniqueInstance() {
        if (!ItemRemoteRepository.uniqueInstance) {
            ItemRemoteRepository.uniqueInstance = new ItemRemoteRepository();
        }
        return ItemRemoteRepository.uniqueInstance;
    }

}