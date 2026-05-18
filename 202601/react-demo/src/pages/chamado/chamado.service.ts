import { requester, Requester } from "../../utils/requester";
import { type Chamado } from "../model/Chamado";


//class ChamadoService extends Requester<Chamado> {

//    constructor(instance: AxiosInstance) {
//        super(instance, "/v1/chamados");
//    }
//}

const chamadoService = new Requester<Chamado>(requester, "/v1/chamados");

export default chamadoService;