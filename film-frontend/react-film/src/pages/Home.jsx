import Films from '../components/Films'
import AddFilmForm from '../components/AddFilmForm'

function Home() {


    return (
        <>
            <h1>This is the Home page to display all films</h1>
            <br />
            <Films/>
            <AddFilmForm />


        </>
    )
}

export default Home