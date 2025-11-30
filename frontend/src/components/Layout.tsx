import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import { Terminal, User } from 'lucide-react';

export const Layout: React.FC = () => {
    return (
        <div className="min-h-screen flex flex-col">
            <header className="border-b border-slate-800 bg-slate-950 p-4">
                <div className="container mx-auto flex items-center justify-between">
                    <div className="flex items-center gap-2 font-bold text-xl text-emerald-400">
                        <Terminal className="w-6 h-6" />
                        <span>DevOps Game Night</span>
                    </div>
                    <nav className="flex gap-6">
                        <Link to="/" className="hover:text-emerald-400 transition-colors">Dashboard</Link>
                        <Link to="/challenges" className="hover:text-emerald-400 transition-colors">Challenges</Link>
                    </nav>
                    <div className="flex items-center gap-2 text-slate-400">
                        <User className="w-5 h-5" />
                        <span>Player 1</span>
                    </div>
                </div>
            </header>
            <main className="flex-1 container mx-auto p-6">
                <Outlet />
            </main>
        </div>
    );
};
