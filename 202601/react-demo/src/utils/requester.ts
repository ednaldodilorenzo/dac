import { create } from 'axios'

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