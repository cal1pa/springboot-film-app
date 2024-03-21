import FilmComp from "../components/FilmComp"
import { useParams } from "react-router-dom"
function Film() {
    const { filmId } = useParams();

    return (
        <>
        <h1>This is the page film details are rendered after being clicked</h1>
        <FilmComp filmId={filmId}/>
        </>
    )
}

export default Film