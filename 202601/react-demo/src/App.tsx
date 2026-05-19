import { RouterProvider } from 'react-router/dom';
import './App.css'
import { router } from './router';
import { AuthContextProvider } from './contexts/AuthContext';


function App() {
  return (
    <AuthContextProvider>
      <RouterProvider router={router} />
    </AuthContextProvider>
  );
}

export default App
