import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [links, setLinks] = useState([])

  const fetchLinks = async () => {
    let response = await fetch("http://localhost:8080/allLinks")
    let data = await response.json()

    setLinks(data)
  }

  useEffect (() => {
    fetchLinks()
  }, [])

  return (
    <div>
      {links.map(el => {
        return <a href={el.url}>{el.name}</a>
      })}

    </div>
  )
}

export default App
