import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Layout } from './components/Layout';
import { Dashboard } from './pages/Dashboard';
import { Challenges } from './pages/Challenges';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<Dashboard />} />
                    <Route path="challenges" element={<Challenges />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
