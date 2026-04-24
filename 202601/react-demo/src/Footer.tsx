
interface FooterProps {
    ano: number,
    children?: React.ReactNode
}

export default function Footer({ ano, children }: FooterProps) {
  return (
    <footer>
      <p>Copyright &copy; {ano}</p>
      {children}
    </footer>
  )
}