import React, { useEffect, useState } from 'react';
import { api, Message } from '../api';
import { Bot, Send } from 'lucide-react';

export const Dashboard: React.FC = () => {
    const [messages, setMessages] = useState<Message[]>([]);
    const [input, setInput] = useState('');

    useEffect(() => {
        // Hardcoded user ID for MVP
        api.get<Message[]>('/messages?userId=u1').then(res => setMessages(res.data)).catch(console.error);
    }, []);

    const sendMessage = async () => {
        if (!input.trim()) return;
        // Mock send for now
        setInput('');
    };

    return (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            <div className="md:col-span-2 space-y-6">
                <div className="bg-slate-800 rounded-lg p-6 border border-slate-700">
                    <h2 className="text-2xl font-bold mb-4">Your Progress</h2>
                    <div className="grid grid-cols-3 gap-4 text-center">
                        <div className="p-4 bg-slate-900 rounded">
                            <div className="text-3xl font-bold text-emerald-400">7</div>
                            <div className="text-sm text-slate-400">Day Streak</div>
                        </div>
                        <div className="p-4 bg-slate-900 rounded">
                            <div className="text-3xl font-bold text-blue-400">12</div>
                            <div className="text-sm text-slate-400">Challenges Done</div>
                        </div>
                        <div className="p-4 bg-slate-900 rounded">
                            <div className="text-3xl font-bold text-purple-400">Level 3</div>
                            <div className="text-sm text-slate-400">DevOps Engineer</div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="bg-slate-800 rounded-lg border border-slate-700 flex flex-col h-[600px]">
                <div className="p-4 border-b border-slate-700 font-bold flex items-center gap-2">
                    <Bot className="w-5 h-5 text-emerald-400" />
                    Game Master
                </div>
                <div className="flex-1 overflow-y-auto p-4 space-y-4">
                    {messages.map(msg => (
                        <div key={msg.id} className={`flex ${msg.role === 'USER' ? 'justify-end' : 'justify-start'}`}>
                            <div className={`max-w-[80%] rounded p-3 ${msg.role === 'USER' ? 'bg-blue-600' :
                                    msg.role === 'AI' ? 'bg-slate-700' : 'bg-slate-900 italic text-sm'
                                }`}>
                                {msg.text}
                            </div>
                        </div>
                    ))}
                    {messages.length === 0 && <div className="text-center text-slate-500 mt-10">No messages yet.</div>}
                </div>
                <div className="p-4 border-t border-slate-700 flex gap-2">
                    <input
                        className="flex-1 bg-slate-900 border border-slate-600 rounded px-3 py-2 focus:outline-none focus:border-emerald-400"
                        placeholder="Say something..."
                        value={input}
                        onChange={e => setInput(e.target.value)}
                        onKeyDown={e => e.key === 'Enter' && sendMessage()}
                    />
                    <button onClick={sendMessage} className="p-2 bg-emerald-600 rounded hover:bg-emerald-500">
                        <Send className="w-5 h-5" />
                    </button>
                </div>
            </div>
        </div>
    );
};
