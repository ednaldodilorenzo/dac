import { create } from 'axios'

export const requester = create({
    baseURL: "/api",
});

requester.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});