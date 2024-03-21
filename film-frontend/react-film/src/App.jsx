import {Routes, Route} from 'react-router-dom'
import Home from './pages/Home'
import Film from './pages/Film'
import FilmNotFound from './pages/FilmNotFound';


function App() {

  return (
    <>
    <Routes>
      <Route path='/' element={<Home/>} />
      <Route path='/films/:filmId' element={<Film/>} />
      <Route path='/film-not-found' element={<FilmNotFound/>} /> 

    </Routes>

    </>
  )
}

export default App
