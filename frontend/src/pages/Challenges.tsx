import React, { useEffect, useState } from 'react';
import { api, Challenge } from '../api';
import { Circle } from 'lucide-react';

export const Challenges: React.FC = () => {
    const [challenges, setChallenges] = useState<Challenge[]>([]);

    useEffect(() => {
        api.get<Challenge[]>('/challenges').then(res => setChallenges(res.data)).catch(console.error);
    }, []);

    return (
        <div className="max-w-4xl mx-auto">
            <h1 className="text-3xl font-bold mb-8">Active Challenges</h1>
            <div className="grid gap-4">
                {challenges.map(challenge => (
                    <div key={challenge.id} className="bg-slate-800 p-6 rounded-lg border border-slate-700 flex items-start justify-between group hover:border-emerald-500 transition-colors">
                        <div>
                            <div className="flex items-center gap-3 mb-2">
                                <span className={`text-xs font-bold px-2 py-1 rounded ${challenge.cadence === 'DAILY' ? 'bg-orange-900 text-orange-200' : 'bg-blue-900 text-blue-200'
                                    }`}>
                                    {challenge.cadence}
                                </span>
                                <h3 className="text-xl font-bold">{challenge.title}</h3>
                            </div>
                            <p className="text-slate-400">{challenge.description}</p>
                        </div>
                        <button className="text-slate-500 hover:text-emerald-400 transition-colors">
                            <Circle className="w-8 h-8" />
                        </button>
                    </div>
                ))}
                {challenges.length === 0 && (
                    <div className="text-center p-10 text-slate-500">No challenges found. Seed the database!</div>
                )}
            </div>
        </div>
    );
};
