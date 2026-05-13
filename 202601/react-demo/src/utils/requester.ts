import { create } from 'axios'

export const requester = create({
    baseURL: "/api",
})