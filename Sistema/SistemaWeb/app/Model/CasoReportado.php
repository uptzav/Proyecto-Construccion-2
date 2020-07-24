<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;

class CasoReportado extends Model
{
    protected $table = "tbl_reporte_caso";
    protected $primaryKey = "idReporteCaso";
    protected $fillable = [
        'tipoCaso',
        'apellidoPaciente',
        'nombrePaciente',
        'sexo',
        'edad',
        'celular',
        'distrito',
        'direccion',
        'otrasObservaciones'

        

    ];
    public $timestamps = false;

    public static function CasoReportadoRegistrar(Request $request)
    {
        $obj = new CasoReportado();
        $obj->tipoCaso = $request->input('tipoCaso');
        $obj->apellidoPaciente = $request->input('apellidoPaciente');
        $obj->nombrePaciente = $request->input('nombrePaciente');
        $obj->sexo = $request->input('sexo');
        $obj->edad = $request->input('edad');
        $obj->celular = $request->input('celular');
        $obj->distrito = $request->input('distrito');
        $obj->direccion = $request->input('direccion');
        $obj->otrasObservaciones = $request->input('otrasObservaciones');
        $obj->save();
    }
}
