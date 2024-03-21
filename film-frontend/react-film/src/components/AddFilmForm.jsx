import React, { useState } from 'react';

function AddFilmForm({ onFilmAdded }) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [releaseYear, setReleaseYear] = useState('');
    const [language, setLanguage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const film = { title, description, releaseYear, language };

        const response = await fetch('http://localhost:8080/films/allFilms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(film),
        });

        if (response.ok) {
            const addedFilm = await response.json();
            console.log('Film added:', addedFilm); 
            // onFilmAdded(addedFilm);
            setTitle('');
            setDescription('');
            setReleaseYear('');
            setLanguage('');
        } else {
            console.error("Failed to add film");
        }
    };
    

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={title} placeholder="Title" onChange={e => setTitle(e.target.value)} required />
            <input type="text" value={description} placeholder="Description" onChange={e => setDescription(e.target.value)} required />
            <input type="text" value={releaseYear} placeholder="Release Year" onChange={e => setReleaseYear(e.target.value)} required />
            <input type="text" value={language} placeholder="Language" onChange={e => setLanguage(e.target.value)} required />
            <button type="submit">Add Film</button>
        </form>
    );
}

export default AddFilmForm;
