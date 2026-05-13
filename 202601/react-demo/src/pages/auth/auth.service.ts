import { requester } from "../../utils/requester";

type LoginResp = {
    token: string;
}

export function executeLogin(username: string, password: string) {
    return requester.post<LoginResp>("/v1/auth/login", { username, password })
    .then(resp => {
        const token = resp.data.token;
        localStorage.setItem("token", token);     
    });
}