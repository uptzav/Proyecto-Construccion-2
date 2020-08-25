<?php

namespace App\Http\Controllers;

use Auth;
use Illuminate\Http\Request;
use Redirect;

class AutenticacionController extends Controller
{
    public function Login()
    {
        return view('Autenticacion.Login');

    }

    public function LoginValidar(Request $request)
    {
        if (Auth::attempt(['email' => $request->input('email'), 'password' => $request->input('password')])) {
            return Redirect::intended('CasosReportado');
        } else {
            // Si los datos no son los correctos volvemos al login y mostramos un error
            return Redirect::back()->with('error_message', 'Invalid data')->withInput();
        }
    }

    public function CerrarSesion()
    {
        // Cerramos la sesión
        Auth::logout();
        // Volvemos al login
        return Redirect::to('/');
    }
}
