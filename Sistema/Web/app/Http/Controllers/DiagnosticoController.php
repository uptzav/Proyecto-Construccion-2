<?php

namespace App\Http\Controllers;

use App\Model\Diagnostico;
use Illuminate\Http\Request;

class DiagnosticoController extends Controller
{
    public function DiagnosticosListar(){
        $diagnosticos = Diagnostico::all();
        return view('Administrador.Diagnostico.Listar',compact('diagnosticos'));
    }
    public function DiagnosticoRegistrar(Request $request)
    {
        $status = false;
        $message = "";
        try {
            Diagnostico::DiagnosticoRegistrar($request);
            $status = true;
            $message = "Se ha registrado su diagnostico";
        } catch (\Exception $exception) {
            $message = $exception->getMessage();
        }
        return response()->json(['status' => $status, 'message' => $message]);
    }
}
