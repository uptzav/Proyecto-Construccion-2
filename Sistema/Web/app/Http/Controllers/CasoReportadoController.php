<?php

namespace App\Http\Controllers;

use App\Model\CasoReportado;
use Illuminate\Http\Request;

class CasoReportadoController extends Controller
{
    public function CasosReportadoListar(){
        $casosReportados = CasoReportado::all();
        return view('Administrador.CasoReportado.Listar',compact('casosReportados'));
    }
    public function CasoReportadoRegistrar(Request $request){
        $status =false;
        $message ="";
        try{
            CasoReportado::CasoReportadoRegistrar($request);
            $status = true;
            $message = "Se ha registrado un caso exitosamente";
        }catch (\Exception $exception){
            $message = $exception->getMessage();
        }
        return response()->json(['status'=>$status,'message'=>$message]);
    }
}
