import Auth from './pages/Auth';
import { Toaster } from 'react-hot-toast';

function App() {
  return (
    <div className="App">
      {/* Esse componente gerencia os alertas bonitões */}
      <Toaster position="top-right" reverseOrder={false} />
      <Auth />
    </div>
  );
}

export default App;