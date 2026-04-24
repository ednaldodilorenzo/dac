import './App.css'
import Footer from './Footer.tsx';
import Botao from './Botao.tsx';

function App() {

  return (
    <>
      <h1>Hello, World!</h1>
      <p>Esta é a minha primeira aplicação react.</p>
      <Botao onClick={() => alert('Você clicou no botão!')}>Clique neste botão</Botao>
      <Footer ano={2025}>
        <p>Todos os direitos reservados.</p>
        <p>Este é o meu primeiro footer em React.</p>
      </Footer>
    </>
  )
}

export default App
