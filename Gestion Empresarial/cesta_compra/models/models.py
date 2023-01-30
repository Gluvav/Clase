# -*- coding: utf-8 -*-
from dateutil.relativedelta import *

from odoo import models, fields, api
from datetime import date

class producto(models.Model):
    _name = 'cesta_compra.producto'
    _description = 'cesta_compra.producto'

    name = fields.Char(string='Nombre Producto', required=True)
    cantidad = fields.Integer(string='Cantidad en Stock', required=True)
    tipo_producto = fields.Selection([('vegetal', 'Vegetal'), ('carne', 'Carne'), ('otros', 'Otros')])
    precio_unidad = fields.Float(string='Precio/ud', required=True)

    _sql_constraints = [('nombre', 'unique(nombre)', 'No se pueden repetir los nombres')]

    #producto_id = fields.Many2one('cesta_compra.compra', string='Compras')
    producto_id = fields.One2many('cesta_compra.compra', 'id_inventario', string='Compras')

class cliente(models.Model):
    _name = 'cesta_compra.cliente'
    _description = 'cesta_compra.cliente'

    name = fields.Char(string='Nombre Cliente', required=True)
    apellido1 = fields.Char(string='Apellido 1 Cliente', required=True)
    apellido2 = fields.Char(string='Apellido 2 Cliente')
    direccion = fields.Char(string='Direccion Cliente', required=True)
    dinero_total = fields.Float(string='Dinero Total', required=True)
    fecha_nacimiento = fields.Date(string="Fecha de Nacimiento", required=True)
    edad = fields.Integer(string="Edad", compute='_edad_compute')
    NIF = fields.Char(string='NIF', required=True)
    mayor_edad = fields.Boolean(string='Mayor de Edad', compute='_mayor_edad')

    _sql_constraints = [('NIF', 'unique(NIF)', 'No se pueden repetir los NIF')]

    compras_ids = fields.One2many('cesta_compra.compra', 'id_comprador', string='Compras')

    @api.depends('fecha_nacimiento')
    def _edad_compute(self):
        today = date.today()
        for record in self:
            record.edad = relativedelta(today, record.fecha_nacimiento).years
            if record.edad > 18:
                record.mayor_edad = True
            else:
                record.mayor_edad = False

class compra(models.Model):
    _name = 'cesta_compra.compra'
    _description = 'cesta_compra.compra'

    id_inventario = fields.Many2one('cesta_compra.producto', string='Id del Producto', required=True)
    id_comprador = fields.Many2one('cesta_compra.cliente', string='Id del Cliente', required=True)
    cantidad_comprada = fields.Integer(string='Cantidad del Producto', required=True)
    fecha_compra = fields.Date(string='Fecha de Compra', default=date.today().strftime('%Y-%m-%d'))
    coste_total = fields.Float(string="Coste Total")
    metodo_pago = fields.Selection([('descontar', 'Descontar de la Cuenta'), ('tarjeta', 'Pagar con Tarjeta')], required=True)

    @api.constrains('cantidad_comprada')
    def menos_stock(self):
        for record in self:
            print(record.metodo_pago)
            if record.id_comprador.dinero_total < record.cantidad_comprada * record.id_inventario.precio_unidad or record.id_inventario.cantidad < record.cantidad_comprada:
                break
            else:
                record.id_inventario.cantidad = record.id_inventario.cantidad - record.cantidad_comprada
                if record.metodo_pago == 'descontar':
                    record.id_comprador.dinero_total = record.id_comprador.dinero_total - (record.cantidad_comprada * record.id_inventario.precio_unidad)
                record.coste_total = record.cantidad_comprada * record.id_inventario.precio_unidad

#
#     @api.depends('value')
#     def _value_pc(self):
#         for record in self:
#             record.value2 = float(record.value) / 100