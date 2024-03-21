import { useEffect, useState } from "react";
import {useNavigate} from 'react-router-dom'

export default function FilmComp({ filmId }) {
    const [film, setFilm] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const navigate = useNavigate();

    const fetchFilm = async () => {
        const response = await fetch(`http://localhost:8080/films/allFilms/${filmId}`);
        const data = await response.json();
        setFilm(data);
        setTitle(data.title);
        setDescription(data.description);
    };

    useEffect(() => {
        fetchFilm();
    }, [filmId]);

    const handleEdit = async (e) => {
        e.preventDefault();
        const response = await fetch(`http://localhost:8080/films/allFilms/${filmId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ title, description }),
        });
        if (response.ok) {
            fetchFilm(); 
            setIsEditing(false); 
        }
    };

    console.log(film)

    const handleDelete = async () => {
        const response = await fetch(`http://localhost:8080/films/allFilms/${filmId}`, {
          method: 'DELETE',
        });
        
        if (response.ok) {
          navigate('/film-not-found');
        } else {
          alert("Failed to delete the film.");
        }
      };
      

    return (
        <>
            {isEditing ? (
                <form onSubmit={handleEdit}>
                    <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
                    <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} />
                    <button type="submit">Save</button>
                    <button type="button" onClick={() => setIsEditing(false)}>Cancel</button>
                </form>
            ) : (
                <>
                    <h1>Name: {film?.title}</h1>
                    <h5>Release Year: {film?.releaseYear}</h5>
                    <p>Description: {film?.description}</p>
                    <button onClick={() => setIsEditing(true)}>Edit</button>
                </>
            )}
            <h3>Actors:</h3>
            <ul>
                {film?.actors?.map((actor) => (
                    <li key={actor.actorID}>{actor.firstName} {actor.lastName}</li>
                ))}
            </ul>

            <button onClick={handleDelete}>Delete Film</button>
        </>
    );
}
