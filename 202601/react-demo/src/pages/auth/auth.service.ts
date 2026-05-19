import { requester } from "../../utils/requester";

type LoginResp = {
    token: string;
}

export function executeLogin(username: string, password: string) {
    return requester.post<LoginResp>("/v1/auth/login", { username, password })
    .then(resp => {
        return resp.data.token;             
    });
}