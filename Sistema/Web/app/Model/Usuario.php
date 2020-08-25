<?php

namespace App\Model;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

class Usuario extends Authenticatable
{
    use Notifiable;
    protected $table = "tbl_usuario";
    protected $primaryKey = "idUsuario";
    protected $fillable = [
        'nombre',
        'email',
        'password',
        'estado',
    ];
    public $timestamps = false;
}
