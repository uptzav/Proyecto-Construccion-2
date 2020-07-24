<?php

namespace App\Http\Controllers;

use App\Model\Donacion;
use Illuminate\Http\Request;



class DonacionController extends Controller
{

    
    public function DonacionListar(){
        $donaciones = Donacion::all();
        return view('Administrador.Donacion.Listar',compact('donaciones'));
    }

    


    public function DonacionRegistrar(Request $request)
    {
        $status = false;
        $message = "";
        try {
            Donacion::DonacionRegistrar($request);
            $status = true;
            $message = "Se ha registrado exitosamente una donación";
        } catch (\Exception $exception) {
            $message = $exception->getMessage();
        }
        return response()->json(['status' => $status, 'message' => $message]);
    }
}
