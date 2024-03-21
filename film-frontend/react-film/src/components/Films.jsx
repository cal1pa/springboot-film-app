import { useEffect, useState } from "react"
import { Link } from "react-router-dom";

export default function Films() {
    const [films, setFilms] = useState([]);

    const fetchFilms = async () => {
        const response = await fetch ('http://localhost:8080/films/allFilms')
        setFilms(await response.json())
    }

    useEffect(() => {
        fetchFilms()
    }, [])
    console.log(films)

    return(
        <>
            <table>
                <thead>
                    <tr>
                        <th>Film Id</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Release Year</th>
                        <th>Language</th>
                    </tr>
                </thead>

                <tbody>
                    {films.map((film) => ( 
                        <tr key={film.filmId}>
                            <td>{film.filmId}</td>
                            <td><Link to={`/films/${film.filmId}`}>{film.title}</Link></td>
                            <td>{film.description}</td>
                            <td>{film.language}</td>
                            <td>{film.releaseYear}</td>
                        </tr>
                    ))}
                </tbody>

            </table>
        </>
    )
}