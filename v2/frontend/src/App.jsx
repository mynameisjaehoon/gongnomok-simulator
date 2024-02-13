
import Header from './components/Header';
import { Route, Routes } from 'react-router-dom';

import Home from './components/Home';
import Login from './components/Login';
import Test from './components/Test';
import NewItem from './components/NewItem';

function App() {

  return (
    <>
      <div className='row'>
        <div className='col-1'></div>
        <div className='col-1'></div>
        <div className='col-1'></div>
      </div>
      <Header />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/test' element={<Test />} />
        <Route path='/item/new' element={<NewItem />} />
      </Routes>
    </>
  );
}

export default App