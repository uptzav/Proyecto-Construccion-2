<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;

class Diagnostico extends Model
{
    protected $table = "tbl_diagnostico";
    protected $primaryKey = "idDiagnostico";
    protected $fillable = [
        'email',
        'temperaturaCorporal',
        'sentirseAhora',
        'tienefiebre',
        'tieneTos',
        'fatiga',
        'faltaAlimento',
        'dondeEstaAhora',
    ];
    public $timestamps = false;

    public static function DiagnosticoRegistrar(Request $request)
    {
        $obj = new Diagnostico();
        $obj->email = $request->input('email');
        $obj->temperaturaCorporal = $request->input('temperaturaCorporal');
        $obj->sentirseAhora = $request->input('sentirseAhora');
        $obj->tienefiebre = $request->input('tienefiebre');
        $obj->tieneTos = $request->input('tieneTos');
        $obj->fatiga = $request->input('fatiga');
        $obj->faltaAlimento = $request->input('faltaAlimento');
        $obj->dondeEstaAhora = $request->input('dondeEstaAhora');
        $obj->save();
    }
}


