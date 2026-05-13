
interface FooterProps {
    ano: number,
    nome: string,
    children?: React.ReactNode
}

export default function Footer({ ano, nome, children }: FooterProps) {
  console
  return (
    <footer>
      <p>Copyright &copy; {ano}</p>
      <p>Desenvolvida por {nome}.</p>
      {children}
    </footer>
  )
}