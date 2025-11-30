import axios from 'axios';

export const api = axios.create({
    baseURL: '/api/v1',
});

export interface Challenge {
    id: string;
    title: string;
    description: string;
    cadence: 'DAILY' | 'WEEKLY';
}

export interface Message {
    id: string;
    role: 'SYSTEM' | 'AI' | 'USER';
    text: string;
    createdAt: string;
}
