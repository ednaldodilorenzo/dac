import { create, type AxiosInstance, type AxiosResponse } from 'axios'

const requester = create({
    baseURL: "/api",
});

requester.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export { requester };

export class Requester<T> {

    private instance: AxiosInstance;
    private baseUri: string;

    constructor(instance: AxiosInstance, baseUri: string) {
        this.instance = instance;
        this.baseUri = baseUri;
    }

    getAll(): Promise<AxiosResponse> {
        return this.instance.get<T[]>(this.baseUri);
    } 
}