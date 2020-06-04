import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {ProcessModel} from "../model/process-model";

export class ProcessRemoteRepository extends BasicRemoteRepository<ProcessModel, number> {

    private static uniqueInstance: ProcessRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"PROCESS");
    }

    static getUniqueInstance() {
        if (!ProcessRemoteRepository.uniqueInstance) {
            ProcessRemoteRepository.uniqueInstance = new ProcessRemoteRepository();
        }
        return ProcessRemoteRepository.uniqueInstance;
    }

}